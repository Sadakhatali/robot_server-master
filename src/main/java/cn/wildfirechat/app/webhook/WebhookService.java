package cn.wildfirechat.app.webhook;

import cn.wildfirechat.app.RobotConfig;
import cn.wildfirechat.app.TokenUtils;
import cn.wildfirechat.app.webhook.general.GeneralWebhook;
import cn.wildfirechat.app.webhook.gitee.GiteeWebhook;
import cn.wildfirechat.app.webhook.github.GithubWebhook;
import cn.wildfirechat.app.webhook.gitlab.GitLabWebhook;
import cn.wildfirechat.common.ErrorCode;
import cn.wildfirechat.pojos.Conversation;
import cn.wildfirechat.pojos.InputOutputUserInfo;
import cn.wildfirechat.pojos.MessagePayload;
import cn.wildfirechat.pojos.SendMessageResult;
import cn.wildfirechat.sdk.RobotService;
import cn.wildfirechat.sdk.model.IMResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class WebhookService {
    private static class Pair {
        public String key;
        public long value;

        public Pair(String key, long value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public long getValue() {
            return value;
        }
    }
    private static final Logger LOG = LoggerFactory.getLogger(WebhookService.class);
    @Autowired
    private RobotConfig mRobotConfig;

    private Map<String, IWebhook> webhookMap;

    private Map<String, Pair> userNameCache = new ConcurrentHashMap<>();

    @PostConstruct
    void init() {
        webhookMap = new HashMap<>();

        IWebhook github = new GithubWebhook();
        IWebhook gitlab = new GitLabWebhook();
        IWebhook general = new GeneralWebhook();
        IWebhook gitee = new GiteeWebhook();

        webhookMap.put(general.invokeCommand(), general);
        webhookMap.put(github.invokeCommand(), github);
        webhookMap.put(gitlab.invokeCommand(), gitlab);
        webhookMap.put(gitee.invokeCommand(), gitee);
    }

    public String InvokeCommands() {
        String commands = "";
        for (String command : webhookMap.keySet()) {
            commands += command;
            commands += "\n";
        }

        return commands;
    }

    public boolean handleInvokeCommand(String command, String fromUser, Conversation conversation) {
        if(webhookMap.containsKey(command)) {
            IWebhook webhook = webhookMap.get(command);
            String callback = mRobotConfig.getPublic_addr()
                    + "/robot"
                    + "/webhook"
                    + webhook.invokeCommand()
                    + "/" + TokenUtils.webhookToken(conversation, fromUser);

            String response = "??????????????????webhook????????????\n" + callback;
            if(conversation.getType() == 0) {
                sendMessage(conversation, response);
            } else if(conversation.getType() == 1) {
                Conversation privateConv = new Conversation();
                privateConv.setLine(conversation.getLine());
                privateConv.setType(0);
                privateConv.setTarget(fromUser);
                sendMessage(privateConv, response);
                sendMessage(conversation, "Webhook??????????????????????????????????????????????????????????????????????????????");
            }
            return true;
        }
        return false;
    }

    public Object handleWebhookPost(HttpServletRequest request, String app, String token, String payload) {
        LOG.info("receive callback {}, {}, {}", app, token, payload);
        if(webhookMap.containsKey(app)) {
            IWebhook webhook = webhookMap.get(app);
            String user = TokenUtils.userFromToken(token);
            Conversation conversation = TokenUtils.conversationFromToken(token);
            LOG.info("user {}", user);
            if(user != null) {
                Pair namePair = userNameCache.computeIfAbsent(user, s -> {
                    try {
                        IMResult<InputOutputUserInfo> inputOutputUserInfo = RobotService.getUserInfo(s);
                        if(inputOutputUserInfo != null && inputOutputUserInfo.getErrorCode() == ErrorCode.ERROR_CODE_SUCCESS && inputOutputUserInfo.getResult() != null) {
                            return new Pair(inputOutputUserInfo.getResult().getDisplayName(), System.currentTimeMillis());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return new Pair("user " + s, System.currentTimeMillis());
                });
                if(namePair.getValue() + 24 * 60 * 60 * 1000 < System.currentTimeMillis()) {
                    userNameCache.remove(user);
                }

                return webhook.handleWebhookPost(request, payload, text -> {
                    text = text + "\n\nFrom " + namePair.getKey();
                    sendMessage(conversation, text);
                });
            }
        }
        return "ok";
    }

    private void sendMessage(Conversation conversation, String text) {
        MessagePayload payload = new MessagePayload();
        payload.setType(1);
        payload.setSearchableContent(text);
        IMResult<SendMessageResult> result = null;
        try {
            result = RobotService.sendMessage(mRobotConfig.getIm_id(), conversation, payload);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (result != null) {
            if (result.getErrorCode() == ErrorCode.ERROR_CODE_SUCCESS) {
                LOG.info("Send response success");
            } else {
                LOG.error("Send response error {}", result.getCode());
            }
        } else {
            LOG.error("Send response error");
        }
    }
}
