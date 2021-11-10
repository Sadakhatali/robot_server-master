package cn.wildfirechat.app.webhook.github.pojo;

import com.google.gson.Gson;

//https://developer.github.com/v3/activity/events/types/#watchevent
public class WatchEvent {
    //The action that was performed. Currently, can only be started.
    public String action;
    public Repository repository;
    public User sender;

    public static WatchEvent fromJson(String jsonStr) {
        return new Gson().fromJson(jsonStr, WatchEvent.class);
    }

    public static void main(String[] args) {
        String event = "{\"action\":\"started\",\"repository\":{\"id\":172194045,\"node_id\":\"MDEwOlJlcG9zaXRvcnkxNzIxOTQwNDU=\",\"name\":\"pc-chat\",\"full_name\":\"wildfirechat/pc-chat\",\"private\":false,\"owner\":{\"login\":\"wildfirechat\",\"id\":33191460,\"node_id\":\"MDEyOk9yZ2FuaXphdGlvbjMzMTkxNDYw\",\"avatar_url\":\"https://avatars0.githubusercontent.com/u/33191460?v=4\",\"gravatar_id\":\"\",\"url\":\"https://api.github.com/users/wildfirechat\",\"html_url\":\"https://github.com/wildfirechat\",\"followers_url\":\"https://api.github.com/users/wildfirechat/followers\",\"following_url\":\"https://api.github.com/users/wildfirechat/following{/other_user}\",\"gists_url\":\"https://api.github.com/users/wildfirechat/gists{/gist_id}\",\"starred_url\":\"https://api.github.com/users/wildfirechat/starred{/owner}{/repo}\",\"subscriptions_url\":\"https://api.github.com/users/wildfirechat/subscriptions\",\"organizations_url\":\"https://api.github.com/users/wildfirechat/orgs\",\"repos_url\":\"https://api.github.com/users/wildfirechat/repos\",\"events_url\":\"https://api.github.com/users/wildfirechat/events{/privacy}\",\"received_events_url\":\"https://api.github.com/users/wildfirechat/received_events\",\"type\":\"Organization\",\"site_admin\":false},\"html_url\":\"https://github.com/wildfirechat/pc-chat\",\"description\":\"Open source IM solution. \",\"fork\":false,\"url\":\"https://api.github.com/repos/wildfirechat/pc-chat\",\"forks_url\":\"https://api.github.com/repos/wildfirechat/pc-chat/forks\",\"keys_url\":\"https://api.github.com/repos/wildfirechat/pc-chat/keys{/key_id}\",\"collaborators_url\":\"https://api.github.com/repos/wildfirechat/pc-chat/collaborators{/collaborator}\",\"teams_url\":\"https://api.github.com/repos/wildfirechat/pc-chat/teams\",\"hooks_url\":\"https://api.github.com/repos/wildfirechat/pc-chat/hooks\",\"issue_events_url\":\"https://api.github.com/repos/wildfirechat/pc-chat/issues/events{/number}\",\"events_url\":\"https://api.github.com/repos/wildfirechat/pc-chat/events\",\"assignees_url\":\"https://api.github.com/repos/wildfirechat/pc-chat/assignees{/user}\",\"branches_url\":\"https://api.github.com/repos/wildfirechat/pc-chat/branches{/branch}\",\"tags_url\":\"https://api.github.com/repos/wildfirechat/pc-chat/tags\",\"blobs_url\":\"https://api.github.com/repos/wildfirechat/pc-chat/git/blobs{/sha}\",\"git_tags_url\":\"https://api.github.com/repos/wildfirechat/pc-chat/git/tags{/sha}\",\"git_refs_url\":\"https://api.github.com/repos/wildfirechat/pc-chat/git/refs{/sha}\",\"trees_url\":\"https://api.github.com/repos/wildfirechat/pc-chat/git/trees{/sha}\",\"statuses_url\":\"https://api.github.com/repos/wildfirechat/pc-chat/statuses/{sha}\",\"languages_url\":\"https://api.github.com/repos/wildfirechat/pc-chat/languages\",\"stargazers_url\":\"https://api.github.com/repos/wildfirechat/pc-chat/stargazers\",\"contributors_url\":\"https://api.github.com/repos/wildfirechat/pc-chat/contributors\",\"subscribers_url\":\"https://api.github.com/repos/wildfirechat/pc-chat/subscribers\",\"subscription_url\":\"https://api.github.com/repos/wildfirechat/pc-chat/subscription\",\"commits_url\":\"https://api.github.com/repos/wildfirechat/pc-chat/commits{/sha}\",\"git_commits_url\":\"https://api.github.com/repos/wildfirechat/pc-chat/git/commits{/sha}\",\"comments_url\":\"https://api.github.com/repos/wildfirechat/pc-chat/comments{/number}\",\"issue_comment_url\":\"https://api.github.com/repos/wildfirechat/pc-chat/issues/comments{/number}\",\"contents_url\":\"https://api.github.com/repos/wildfirechat/pc-chat/contents/{+path}\",\"compare_url\":\"https://api.github.com/repos/wildfirechat/pc-chat/compare/{base}...{head}\",\"merges_url\":\"https://api.github.com/repos/wildfirechat/pc-chat/merges\",\"archive_url\":\"https://api.github.com/repos/wildfirechat/pc-chat/{archive_format}{/ref}\",\"downloads_url\":\"https://api.github.com/repos/wildfirechat/pc-chat/downloads\",\"issues_url\":\"https://api.github.com/repos/wildfirechat/pc-chat/issues{/number}\",\"pulls_url\":\"https://api.github.com/repos/wildfirechat/pc-chat/pulls{/number}\",\"milestones_url\":\"https://api.github.com/repos/wildfirechat/pc-chat/milestones{/number}\",\"notifications_url\":\"https://api.github.com/repos/wildfirechat/pc-chat/notifications{?since,all,participating}\",\"labels_url\":\"https://api.github.com/repos/wildfirechat/pc-chat/labels{/name}\",\"releases_url\":\"https://api.github.com/repos/wildfirechat/pc-chat/releases{/id}\",\"deployments_url\":\"https://api.github.com/repos/wildfirechat/pc-chat/deployments\",\"created_at\":\"2019-02-23T09:08:03Z\",\"updated_at\":\"2020-02-24T01:15:25Z\",\"pushed_at\":\"2020-02-23T08:31:54Z\",\"git_url\":\"git://github.com/wildfirechat/pc-chat.git\",\"ssh_url\":\"git@github.com:wildfirechat/pc-chat.git\",\"clone_url\":\"https://github.com/wildfirechat/pc-chat.git\",\"svn_url\":\"https://github.com/wildfirechat/pc-chat\",\"homepage\":\"http://docs.wildfirechat.cn\",\"size\":172881,\"stargazers_count\":268,\"watchers_count\":268,\"language\":\"JavaScript\",\"has_issues\":true,\"has_projects\":true,\"has_downloads\":true,\"has_wiki\":true,\"has_pages\":false,\"forks_count\":199,\"mirror_url\":null,\"archived\":false,\"disabled\":false,\"open_issues_count\":7,\"license\":{\"key\":\"mit\",\"name\":\"MIT License\",\"spdx_id\":\"MIT\",\"url\":\"https://api.github.com/licenses/mit\",\"node_id\":\"MDc6TGljZW5zZTEz\"},\"forks\":199,\"open_issues\":7,\"watchers\":268,\"default_branch\":\"master\"},\"organization\":{\"login\":\"wildfirechat\",\"id\":33191460,\"node_id\":\"MDEyOk9yZ2FuaXphdGlvbjMzMTkxNDYw\",\"url\":\"https://api.github.com/orgs/wildfirechat\",\"repos_url\":\"https://api.github.com/orgs/wildfirechat/repos\",\"events_url\":\"https://api.github.com/orgs/wildfirechat/events\",\"hooks_url\":\"https://api.github.com/orgs/wildfirechat/hooks\",\"issues_url\":\"https://api.github.com/orgs/wildfirechat/issues\",\"members_url\":\"https://api.github.com/orgs/wildfirechat/members{/member}\",\"public_members_url\":\"https://api.github.com/orgs/wildfirechat/public_members{/member}\",\"avatar_url\":\"https://avatars0.githubusercontent.com/u/33191460?v=4\",\"description\":\"北京野火无限网络科技有限公司--专注于即时通讯技术，提供优质可控的即时通讯能力\"},\"sender\":{\"login\":\"longshadian\",\"id\":1725317,\"node_id\":\"MDQ6VXNlcjE3MjUzMTc=\",\"avatar_url\":\"https://avatars0.githubusercontent.com/u/1725317?v=4\",\"gravatar_id\":\"\",\"url\":\"https://api.github.com/users/longshadian\",\"html_url\":\"https://github.com/longshadian\",\"followers_url\":\"https://api.github.com/users/longshadian/followers\",\"following_url\":\"https://api.github.com/users/longshadian/following{/other_user}\",\"gists_url\":\"https://api.github.com/users/longshadian/gists{/gist_id}\",\"starred_url\":\"https://api.github.com/users/longshadian/starred{/owner}{/repo}\",\"subscriptions_url\":\"https://api.github.com/users/longshadian/subscriptions\",\"organizations_url\":\"https://api.github.com/users/longshadian/orgs\",\"repos_url\":\"https://api.github.com/users/longshadian/repos\",\"events_url\":\"https://api.github.com/users/longshadian/events{/privacy}\",\"received_events_url\":\"https://api.github.com/users/longshadian/received_events\",\"type\":\"User\",\"site_admin\":false}}";
        WatchEvent watchEvent = fromJson(event);
        System.out.println(watchEvent.action);
    }
}
