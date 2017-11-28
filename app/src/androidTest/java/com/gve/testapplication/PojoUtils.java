package com.gve.testapplication;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gve.testapplication.ListOfRepoFeature.data.MapperRepoRawToRepo;
import com.gve.testapplication.ListOfRepoFeature.data.RepoRaw;
import com.gve.testapplication.ListOfRepoFeature.data.Repository;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

/**
 * Created by gve on 28/11/2017.
 */

public class PojoUtils {

    public static final String OWNER_RAW = "{\n" +
            "      \"login\": \"xing\",\n" +
            "      \"id\": 27901,\n" +
            "      \"avatar_url\": \"https://avatars2.githubusercontent.com/u/27901?v=4\",\n" +
            "      \"gravatar_id\": \"\",\n" +
            "      \"url\": \"https://api.github.com/users/xing\",\n" +
            "      \"html_url\": \"https://github.com/xing\",\n" +
            "      \"followers_url\": \"https://api.github.com/users/xing/followers\",\n" +
            "      \"following_url\": \"https://api.github.com/users/xing/following{/other_user}\",\n" +
            "      \"gists_url\": \"https://api.github.com/users/xing/gists{/gist_id}\",\n" +
            "      \"starred_url\": \"https://api.github.com/users/xing/starred{/owner}{/repo}\",\n" +
            "      \"subscriptions_url\": \"https://api.github.com/users/xing/subscriptions\",\n" +
            "      \"organizations_url\": \"https://api.github.com/users/xing/orgs\",\n" +
            "      \"repos_url\": \"https://api.github.com/users/xing/repos\",\n" +
            "      \"events_url\": \"https://api.github.com/users/xing/events{/privacy}\",\n" +
            "      \"received_events_url\": \"https://api.github.com/users/xing/received_events\",\n" +
            "      \"type\": \"Organization\",\n" +
            "      \"site_admin\": false\n" +
            "    }";

    public static final String OWNER_RAW_LOGIN = "xing";
    public static final long OWNER_RAW_ID = 27901L;

    public static final String LIST_2_REPO_RAW = "[\n" +
            "  {\n" +
            "    \"id\": 493890,\n" +
            "    \"name\": \"alter_table\",\n" +
            "    \"full_name\": \"xing/alter_table\",\n" +
            "    \"owner\": {\n" +
            "      \"login\": \"xing\",\n" +
            "      \"id\": 27901,\n" +
            "      \"avatar_url\": \"https://avatars2.githubusercontent.com/u/27901?v=4\",\n" +
            "      \"gravatar_id\": \"\",\n" +
            "      \"url\": \"https://api.github.com/users/xing\",\n" +
            "      \"html_url\": \"https://github.com/xing\",\n" +
            "      \"followers_url\": \"https://api.github.com/users/xing/followers\",\n" +
            "      \"following_url\": \"https://api.github.com/users/xing/following{/other_user}\",\n" +
            "      \"gists_url\": \"https://api.github.com/users/xing/gists{/gist_id}\",\n" +
            "      \"starred_url\": \"https://api.github.com/users/xing/starred{/owner}{/repo}\",\n" +
            "      \"subscriptions_url\": \"https://api.github.com/users/xing/subscriptions\",\n" +
            "      \"organizations_url\": \"https://api.github.com/users/xing/orgs\",\n" +
            "      \"repos_url\": \"https://api.github.com/users/xing/repos\",\n" +
            "      \"events_url\": \"https://api.github.com/users/xing/events{/privacy}\",\n" +
            "      \"received_events_url\": \"https://api.github.com/users/xing/received_events\",\n" +
            "      \"type\": \"Organization\",\n" +
            "      \"site_admin\": false\n" +
            "    },\n" +
            "    \"private\": false,\n" +
            "    \"html_url\": \"https://github.com/xing/alter_table\",\n" +
            "    \"description\": \"A rails plugin to execute multiple ADD, ALTER, DROP, and CHANGE clauses in a single ALTER TABLE statement.\",\n" +
            "    \"fork\": false,\n" +
            "    \"url\": \"https://api.github.com/repos/xing/alter_table\",\n" +
            "    \"forks_url\": \"https://api.github.com/repos/xing/alter_table/forks\",\n" +
            "    \"keys_url\": \"https://api.github.com/repos/xing/alter_table/keys{/key_id}\",\n" +
            "    \"collaborators_url\": \"https://api.github.com/repos/xing/alter_table/collaborators{/collaborator}\",\n" +
            "    \"teams_url\": \"https://api.github.com/repos/xing/alter_table/teams\",\n" +
            "    \"hooks_url\": \"https://api.github.com/repos/xing/alter_table/hooks\",\n" +
            "    \"issue_events_url\": \"https://api.github.com/repos/xing/alter_table/issues/events{/number}\",\n" +
            "    \"events_url\": \"https://api.github.com/repos/xing/alter_table/events\",\n" +
            "    \"assignees_url\": \"https://api.github.com/repos/xing/alter_table/assignees{/user}\",\n" +
            "    \"branches_url\": \"https://api.github.com/repos/xing/alter_table/branches{/branch}\",\n" +
            "    \"tags_url\": \"https://api.github.com/repos/xing/alter_table/tags\",\n" +
            "    \"blobs_url\": \"https://api.github.com/repos/xing/alter_table/git/blobs{/sha}\",\n" +
            "    \"git_tags_url\": \"https://api.github.com/repos/xing/alter_table/git/tags{/sha}\",\n" +
            "    \"git_refs_url\": \"https://api.github.com/repos/xing/alter_table/git/refs{/sha}\",\n" +
            "    \"trees_url\": \"https://api.github.com/repos/xing/alter_table/git/trees{/sha}\",\n" +
            "    \"statuses_url\": \"https://api.github.com/repos/xing/alter_table/statuses/{sha}\",\n" +
            "    \"languages_url\": \"https://api.github.com/repos/xing/alter_table/languages\",\n" +
            "    \"stargazers_url\": \"https://api.github.com/repos/xing/alter_table/stargazers\",\n" +
            "    \"contributors_url\": \"https://api.github.com/repos/xing/alter_table/contributors\",\n" +
            "    \"subscribers_url\": \"https://api.github.com/repos/xing/alter_table/subscribers\",\n" +
            "    \"subscription_url\": \"https://api.github.com/repos/xing/alter_table/subscription\",\n" +
            "    \"commits_url\": \"https://api.github.com/repos/xing/alter_table/commits{/sha}\",\n" +
            "    \"git_commits_url\": \"https://api.github.com/repos/xing/alter_table/git/commits{/sha}\",\n" +
            "    \"comments_url\": \"https://api.github.com/repos/xing/alter_table/comments{/number}\",\n" +
            "    \"issue_comment_url\": \"https://api.github.com/repos/xing/alter_table/issues/comments{/number}\",\n" +
            "    \"contents_url\": \"https://api.github.com/repos/xing/alter_table/contents/{+path}\",\n" +
            "    \"compare_url\": \"https://api.github.com/repos/xing/alter_table/compare/{base}...{head}\",\n" +
            "    \"merges_url\": \"https://api.github.com/repos/xing/alter_table/merges\",\n" +
            "    \"archive_url\": \"https://api.github.com/repos/xing/alter_table/{archive_format}{/ref}\",\n" +
            "    \"downloads_url\": \"https://api.github.com/repos/xing/alter_table/downloads\",\n" +
            "    \"issues_url\": \"https://api.github.com/repos/xing/alter_table/issues{/number}\",\n" +
            "    \"pulls_url\": \"https://api.github.com/repos/xing/alter_table/pulls{/number}\",\n" +
            "    \"milestones_url\": \"https://api.github.com/repos/xing/alter_table/milestones{/number}\",\n" +
            "    \"notifications_url\": \"https://api.github.com/repos/xing/alter_table/notifications{?since,all,participating}\",\n" +
            "    \"labels_url\": \"https://api.github.com/repos/xing/alter_table/labels{/name}\",\n" +
            "    \"releases_url\": \"https://api.github.com/repos/xing/alter_table/releases{/id}\",\n" +
            "    \"deployments_url\": \"https://api.github.com/repos/xing/alter_table/deployments\",\n" +
            "    \"created_at\": \"2010-01-29T17:14:47Z\",\n" +
            "    \"updated_at\": \"2015-11-05T13:10:11Z\",\n" +
            "    \"pushed_at\": \"2013-08-07T08:32:48Z\",\n" +
            "    \"git_url\": \"git://github.com/xing/alter_table.git\",\n" +
            "    \"ssh_url\": \"git@github.com:xing/alter_table.git\",\n" +
            "    \"clone_url\": \"https://github.com/xing/alter_table.git\",\n" +
            "    \"svn_url\": \"https://github.com/xing/alter_table\",\n" +
            "    \"homepage\": \"http://devblog.xing.com/ruby/alter-table-rails-plugin/\",\n" +
            "    \"size\": 62,\n" +
            "    \"stargazers_count\": 26,\n" +
            "    \"watchers_count\": 26,\n" +
            "    \"language\": \"Ruby\",\n" +
            "    \"has_issues\": true,\n" +
            "    \"has_projects\": true,\n" +
            "    \"has_downloads\": false,\n" +
            "    \"has_wiki\": false,\n" +
            "    \"has_pages\": false,\n" +
            "    \"forks_count\": 5,\n" +
            "    \"mirror_url\": null,\n" +
            "    \"archived\": false,\n" +
            "    \"open_issues_count\": 1,\n" +
            "    \"forks\": 5,\n" +
            "    \"open_issues\": 1,\n" +
            "    \"watchers\": 26,\n" +
            "    \"default_branch\": \"master\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": 2637328,\n" +
            "    \"name\": \"amiando\",\n" +
            "    \"full_name\": \"xing/amiando\",\n" +
            "    \"owner\": {\n" +
            "      \"login\": \"xing\",\n" +
            "      \"id\": 27901,\n" +
            "      \"avatar_url\": \"https://avatars2.githubusercontent.com/u/27901?v=4\",\n" +
            "      \"gravatar_id\": \"\",\n" +
            "      \"url\": \"https://api.github.com/users/xing\",\n" +
            "      \"html_url\": \"https://github.com/xing\",\n" +
            "      \"followers_url\": \"https://api.github.com/users/xing/followers\",\n" +
            "      \"following_url\": \"https://api.github.com/users/xing/following{/other_user}\",\n" +
            "      \"gists_url\": \"https://api.github.com/users/xing/gists{/gist_id}\",\n" +
            "      \"starred_url\": \"https://api.github.com/users/xing/starred{/owner}{/repo}\",\n" +
            "      \"subscriptions_url\": \"https://api.github.com/users/xing/subscriptions\",\n" +
            "      \"organizations_url\": \"https://api.github.com/users/xing/orgs\",\n" +
            "      \"repos_url\": \"https://api.github.com/users/xing/repos\",\n" +
            "      \"events_url\": \"https://api.github.com/users/xing/events{/privacy}\",\n" +
            "      \"received_events_url\": \"https://api.github.com/users/xing/received_events\",\n" +
            "      \"type\": \"Organization\",\n" +
            "      \"site_admin\": false\n" +
            "    },\n" +
            "    \"private\": false,\n" +
            "    \"html_url\": \"https://github.com/xing/amiando\",\n" +
            "    \"description\": \"Amiando Api Ruby implementation\",\n" +
            "    \"fork\": false,\n" +
            "    \"url\": \"https://api.github.com/repos/xing/amiando\",\n" +
            "    \"forks_url\": \"https://api.github.com/repos/xing/amiando/forks\",\n" +
            "    \"keys_url\": \"https://api.github.com/repos/xing/amiando/keys{/key_id}\",\n" +
            "    \"collaborators_url\": \"https://api.github.com/repos/xing/amiando/collaborators{/collaborator}\",\n" +
            "    \"teams_url\": \"https://api.github.com/repos/xing/amiando/teams\",\n" +
            "    \"hooks_url\": \"https://api.github.com/repos/xing/amiando/hooks\",\n" +
            "    \"issue_events_url\": \"https://api.github.com/repos/xing/amiando/issues/events{/number}\",\n" +
            "    \"events_url\": \"https://api.github.com/repos/xing/amiando/events\",\n" +
            "    \"assignees_url\": \"https://api.github.com/repos/xing/amiando/assignees{/user}\",\n" +
            "    \"branches_url\": \"https://api.github.com/repos/xing/amiando/branches{/branch}\",\n" +
            "    \"tags_url\": \"https://api.github.com/repos/xing/amiando/tags\",\n" +
            "    \"blobs_url\": \"https://api.github.com/repos/xing/amiando/git/blobs{/sha}\",\n" +
            "    \"git_tags_url\": \"https://api.github.com/repos/xing/amiando/git/tags{/sha}\",\n" +
            "    \"git_refs_url\": \"https://api.github.com/repos/xing/amiando/git/refs{/sha}\",\n" +
            "    \"trees_url\": \"https://api.github.com/repos/xing/amiando/git/trees{/sha}\",\n" +
            "    \"statuses_url\": \"https://api.github.com/repos/xing/amiando/statuses/{sha}\",\n" +
            "    \"languages_url\": \"https://api.github.com/repos/xing/amiando/languages\",\n" +
            "    \"stargazers_url\": \"https://api.github.com/repos/xing/amiando/stargazers\",\n" +
            "    \"contributors_url\": \"https://api.github.com/repos/xing/amiando/contributors\",\n" +
            "    \"subscribers_url\": \"https://api.github.com/repos/xing/amiando/subscribers\",\n" +
            "    \"subscription_url\": \"https://api.github.com/repos/xing/amiando/subscription\",\n" +
            "    \"commits_url\": \"https://api.github.com/repos/xing/amiando/commits{/sha}\",\n" +
            "    \"git_commits_url\": \"https://api.github.com/repos/xing/amiando/git/commits{/sha}\",\n" +
            "    \"comments_url\": \"https://api.github.com/repos/xing/amiando/comments{/number}\",\n" +
            "    \"issue_comment_url\": \"https://api.github.com/repos/xing/amiando/issues/comments{/number}\",\n" +
            "    \"contents_url\": \"https://api.github.com/repos/xing/amiando/contents/{+path}\",\n" +
            "    \"compare_url\": \"https://api.github.com/repos/xing/amiando/compare/{base}...{head}\",\n" +
            "    \"merges_url\": \"https://api.github.com/repos/xing/amiando/merges\",\n" +
            "    \"archive_url\": \"https://api.github.com/repos/xing/amiando/{archive_format}{/ref}\",\n" +
            "    \"downloads_url\": \"https://api.github.com/repos/xing/amiando/downloads\",\n" +
            "    \"issues_url\": \"https://api.github.com/repos/xing/amiando/issues{/number}\",\n" +
            "    \"pulls_url\": \"https://api.github.com/repos/xing/amiando/pulls{/number}\",\n" +
            "    \"milestones_url\": \"https://api.github.com/repos/xing/amiando/milestones{/number}\",\n" +
            "    \"notifications_url\": \"https://api.github.com/repos/xing/amiando/notifications{?since,all,participating}\",\n" +
            "    \"labels_url\": \"https://api.github.com/repos/xing/amiando/labels{/name}\",\n" +
            "    \"releases_url\": \"https://api.github.com/repos/xing/amiando/releases{/id}\",\n" +
            "    \"deployments_url\": \"https://api.github.com/repos/xing/amiando/deployments\",\n" +
            "    \"created_at\": \"2011-10-24T16:09:04Z\",\n" +
            "    \"updated_at\": \"2013-12-02T16:19:35Z\",\n" +
            "    \"pushed_at\": \"2013-08-01T18:09:42Z\",\n" +
            "    \"git_url\": \"git://github.com/xing/amiando.git\",\n" +
            "    \"ssh_url\": \"git@github.com:xing/amiando.git\",\n" +
            "    \"clone_url\": \"https://github.com/xing/amiando.git\",\n" +
            "    \"svn_url\": \"https://github.com/xing/amiando\",\n" +
            "    \"homepage\": \"http://rdoc.info/github/xing/amiando/master/frames\",\n" +
            "    \"size\": 521,\n" +
            "    \"stargazers_count\": 8,\n" +
            "    \"watchers_count\": 8,\n" +
            "    \"language\": \"Ruby\",\n" +
            "    \"has_issues\": true,\n" +
            "    \"has_projects\": true,\n" +
            "    \"has_downloads\": true,\n" +
            "    \"has_wiki\": true,\n" +
            "    \"has_pages\": false,\n" +
            "    \"forks_count\": 9,\n" +
            "    \"mirror_url\": null,\n" +
            "    \"archived\": false,\n" +
            "    \"open_issues_count\": 4,\n" +
            "    \"forks\": 9,\n" +
            "    \"open_issues\": 4,\n" +
            "    \"watchers\": 8,\n" +
            "    \"default_branch\": \"master\"\n" +
            "  }\n" +
            "]";

    public static Single<List<Repository>> getList(Gson gson) {
        Type listType = new TypeToken<ArrayList<RepoRaw>>(){}.getType();
        List<RepoRaw> listRepoRaw = gson.fromJson(PojoUtils.LIST_2_REPO_RAW, listType);
        return Single.just(listRepoRaw)
                .flatMap(MapperRepoRawToRepo.INSTANCE.getMapperListRepoRawToRepo());
    }

    public static Single<List<RepoRaw>> getListRaw(Gson gson) {
        Type listType = new TypeToken<ArrayList<RepoRaw>>(){}.getType();
        List<RepoRaw> listRepoRaw = gson.fromJson(PojoUtils.LIST_2_REPO_RAW, listType);
        return Single.just(listRepoRaw);
    }
}
