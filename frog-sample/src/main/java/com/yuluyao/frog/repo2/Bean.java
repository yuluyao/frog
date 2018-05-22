package com.yuluyao.frog.repo2;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Bean {
    /**
     * id : 7647451679
     * type : PushEvent
     * actor : {"id":38841491,"login":"KentoNishi","display_login":"KentoNishi","gravatar_id":"","url":"https://api.github.com/users/KentoNishi","avatar_url":"https://avatars.githubusercontent.com/u/38841491?"}
     * repo : {"id":131521090,"name":"KentoNishi/kentonishi.github.io","url":"https://api.github.com/repos/KentoNishi/kentonishi.github.io"}
     * payload : {"push_id":2549395509,"size":1,"distinct_size":1,"ref":"refs/heads/master","head":"f9e78a3432876200fee5bbd459a3798a53aab910","before":"cdaf78e4bf00fd690bc641d74ba25c60bfa3ec7b","commits":[{"sha":"f9e78a3432876200fee5bbd459a3798a53aab910","author":{"email":"38841491+KentoNishi@users.noreply.github.com","name":"Kento Nishi"},"message":"Update login.html","distinct":true,"url":"https://api.github.com/repos/KentoNishi/kentonishi.github.io/commits/f9e78a3432876200fee5bbd459a3798a53aab910"}]}
     * public : true
     * created_at : 2018-05-09T04:04:27Z
     */

    private String id;
    private String type;
    private ActorBean actor;
    private RepoBean repo;
    private PayloadBean payload;
    @SerializedName("public") private boolean publicX;
    private String created_at;

    public String getId() { return id;}

    public void setId(String id) { this.id = id;}

    public String getType() { return type;}

    public void setType(String type) { this.type = type;}

    public ActorBean getActor() { return actor;}

    public void setActor(ActorBean actor) { this.actor = actor;}

    public RepoBean getRepo() { return repo;}

    public void setRepo(RepoBean repo) { this.repo = repo;}

    public PayloadBean getPayload() { return payload;}

    public void setPayload(PayloadBean payload) { this.payload = payload;}

    public boolean isPublicX() { return publicX;}

    public void setPublicX(boolean publicX) { this.publicX = publicX;}

    public String getCreated_at() { return created_at;}

    public void setCreated_at(String created_at) { this.created_at = created_at;}

    public static class ActorBean {
        /**
         * id : 38841491
         * login : KentoNishi
         * display_login : KentoNishi
         * gravatar_id :
         * url : https://api.github.com/users/KentoNishi
         * avatar_url : https://avatars.githubusercontent.com/u/38841491?
         */

        private int id;
        private String login;
        private String display_login;
        private String gravatar_id;
        private String url;
        private String avatar_url;

        public int getId() { return id;}

        public void setId(int id) { this.id = id;}

        public String getLogin() { return login;}

        public void setLogin(String login) { this.login = login;}

        public String getDisplay_login() { return display_login;}

        public void setDisplay_login(String display_login) { this.display_login = display_login;}

        public String getGravatar_id() { return gravatar_id;}

        public void setGravatar_id(String gravatar_id) { this.gravatar_id = gravatar_id;}

        public String getUrl() { return url;}

        public void setUrl(String url) { this.url = url;}

        public String getAvatar_url() { return avatar_url;}

        public void setAvatar_url(String avatar_url) { this.avatar_url = avatar_url;}
    }

    public static class RepoBean {
        /**
         * id : 131521090
         * name : KentoNishi/kentonishi.github.io
         * url : https://api.github.com/repos/KentoNishi/kentonishi.github.io
         */

        private int id;
        private String name;
        private String url;

        public int getId() { return id;}

        public void setId(int id) { this.id = id;}

        public String getName() { return name;}

        public void setName(String name) { this.name = name;}

        public String getUrl() { return url;}

        public void setUrl(String url) { this.url = url;}
    }

    public static class PayloadBean {
        /**
         * push_id : 2549395509
         * size : 1
         * distinct_size : 1
         * ref : refs/heads/master
         * head : f9e78a3432876200fee5bbd459a3798a53aab910
         * before : cdaf78e4bf00fd690bc641d74ba25c60bfa3ec7b
         * commits : [{"sha":"f9e78a3432876200fee5bbd459a3798a53aab910","author":{"email":"38841491+KentoNishi@users.noreply.github.com","name":"Kento Nishi"},"message":"Update login.html","distinct":true,"url":"https://api.github.com/repos/KentoNishi/kentonishi.github.io/commits/f9e78a3432876200fee5bbd459a3798a53aab910"}]
         */

        private long push_id;
        private int size;
        private int distinct_size;
        private String ref;
        private String head;
        private String before;
        private List<CommitsBean> commits;

        public long getPush_id() { return push_id;}

        public void setPush_id(long push_id) { this.push_id = push_id;}

        public int getSize() { return size;}

        public void setSize(int size) { this.size = size;}

        public int getDistinct_size() { return distinct_size;}

        public void setDistinct_size(int distinct_size) { this.distinct_size = distinct_size;}

        public String getRef() { return ref;}

        public void setRef(String ref) { this.ref = ref;}

        public String getHead() { return head;}

        public void setHead(String head) { this.head = head;}

        public String getBefore() { return before;}

        public void setBefore(String before) { this.before = before;}

        public List<CommitsBean> getCommits() { return commits;}

        public void setCommits(List<CommitsBean> commits) { this.commits = commits;}

        public static class CommitsBean {
            /**
             * sha : f9e78a3432876200fee5bbd459a3798a53aab910
             * author : {"email":"38841491+KentoNishi@users.noreply.github.com","name":"Kento Nishi"}
             * message : Update login.html
             * distinct : true
             * url : https://api.github.com/repos/KentoNishi/kentonishi.github.io/commits/f9e78a3432876200fee5bbd459a3798a53aab910
             */

            private String sha;
            private AuthorBean author;
            private String message;
            private boolean distinct;
            private String url;

            public String getSha() { return sha;}

            public void setSha(String sha) { this.sha = sha;}

            public AuthorBean getAuthor() { return author;}

            public void setAuthor(AuthorBean author) { this.author = author;}

            public String getMessage() { return message;}

            public void setMessage(String message) { this.message = message;}

            public boolean isDistinct() { return distinct;}

            public void setDistinct(boolean distinct) { this.distinct = distinct;}

            public String getUrl() { return url;}

            public void setUrl(String url) { this.url = url;}

            public static class AuthorBean {
                /**
                 * email : 38841491+KentoNishi@users.noreply.github.com
                 * name : Kento Nishi
                 */

                private String email;
                private String name;

                public String getEmail() { return email;}

                public void setEmail(String email) { this.email = email;}

                public String getName() { return name;}

                public void setName(String name) { this.name = name;}
            }
        }
    }
}
