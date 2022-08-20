package com.qxy.mzbzy.data.bean;

import androidx.room.TypeConverters;

import com.qxy.mzbzy.data.converter.RankConverter;

import java.util.List;
public class Rank {
    private Data data;
    public void setData(Data data) {
        this.data = data;
    }
    public Data getData() {
        return data;
    }
    public class Data {

        private String active_time;
        private String description;
        private String error_code;
        private List<MList> list;
        public void setActive_time(String active_time) {
            this.active_time = active_time;
        }
        public String getActive_time() {
            return active_time;
        }

        public void setDescription(String description) {
            this.description = description;
        }
        public String getDescription() {
            return description;
        }

        public void setError_code(String error_code) {
            this.error_code = error_code;
        }
        public String getError_code() {
            return error_code;
        }

        public void setList(List<MList> mList) {
            this.list = mList;
        }
        public List<MList> getList() {
            return list;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "active_time='" + active_time + '\'' +
                    ", description='" + description + '\'' +
                    ", error_code='" + error_code + '\'' +
                    ", list=" + list +
                    '}';
        }

        public class MList {

            private List<String> actors;
            private List<String> areas;
            private List<String> directors;
            private String discussion_hot;
            private String hot;
            private String id;
            private String influence_hot;
            private String maoyan_id;
            private String name;
            private String name_en;
            private String poster;
            private String release_date;
            private String search_hot;
            private List<String> tags;
            private String topic_hot;
            private String type;

            public String getTagsOut(){
                if (actors.size()<=0){
                    return " ";
                }else if (actors.size()==1){
                    return tags.get(0);
                }else{
                    return tags.get(0)+" / "+tags.get(1);
                }
            }

            public String getUpYearOut(){
                String[] strings = release_date.split("-");
                return strings[0]+" 上映";
            }

            public String getDirectorsOut(){
                if (actors.size()<=0){
                    return " ";
                }else if (actors.size()==1){
                    return tags.get(0);
                }else{
                    return tags.get(0)+" , "+tags.get(1);
                }
            }

            public String getHotOut(){
                return "\uD83D\uDD25"+search_hot;
            }

            public void setActors(List<String> actors) {
                this.actors = actors;
            }
            public List<String> getActors() {
                return actors;
            }

            public void setAreas(List<String> areas) {
                this.areas = areas;
            }
            public List<String> getAreas() {
                return areas;
            }

            public void setDirectors(List<String> directors) {
                this.directors = directors;
            }
            public List<String> getDirectors() {
                return directors;
            }

            public void setDiscussion_hot(String discussion_hot) {
                this.discussion_hot = discussion_hot;
            }
            public String getDiscussion_hot() {
                return discussion_hot;
            }

            public void setHot(String hot) {
                this.hot = hot;
            }
            public String getHot() {
                return hot;
            }

            public void setId(String id) {
                this.id = id;
            }
            public String getId() {
                return id;
            }

            public void setInfluence_hot(String influence_hot) {
                this.influence_hot = influence_hot;
            }
            public String getInfluence_hot() {
                return influence_hot;
            }

            public void setMaoyan_id(String maoyan_id) {
                this.maoyan_id = maoyan_id;
            }
            public String getMaoyan_id() {
                return maoyan_id;
            }

            public void setName(String name) {
                this.name = name;
            }
            public String getName() {
                return name;
            }

            public void setName_en(String name_en) {
                this.name_en = name_en;
            }
            public String getName_en() {
                return name_en;
            }

            public void setPoster(String poster) {
                this.poster = poster;
            }
            public String getPoster() {
                return poster;
            }

            public void setRelease_date(String release_date) {
                this.release_date = release_date;
            }
            public String getRelease_date() {
                return release_date;
            }

            public void setSearch_hot(String search_hot) {
                this.search_hot = search_hot;
            }
            public String getSearch_hot() {
                return search_hot;
            }

            public void setTags(List<String> tags) {
                this.tags = tags;
            }
            public List<String> getTags() {
                return tags;
            }

            public void setTopic_hot(String topic_hot) {
                this.topic_hot = topic_hot;
            }
            public String getTopic_hot() {
                return topic_hot;
            }

            public void setType(String type) {
                this.type = type;
            }
            public String getType() {
                return type;
            }

            @Override
            public String toString() {
                return "mList{" +
                        "actors=" + actors +
                        ", areas=" + areas +
                        ", directors=" + directors +
                        ", discussion_hot='" + discussion_hot + '\'' +
                        ", hot='" + hot + '\'' +
                        ", id='" + id + '\'' +
                        ", influence_hot='" + influence_hot + '\'' +
                        ", maoyan_id='" + maoyan_id + '\'' +
                        ", name='" + name + '\'' +
                        ", name_en='" + name_en + '\'' +
                        ", poster='" + poster + '\'' +
                        ", release_date='" + release_date + '\'' +
                        ", search_hot='" + search_hot + '\'' +
                        ", tags=" + tags +
                        ", topic_hot='" + topic_hot + '\'' +
                        ", type='" + type + '\'' +
                        '}';
            }
        }

    }
}





// 成功
//{
//  "data": {
//    "active_time": "2020-03-31 12:00:00",
//    "description": "",
//    "error_code": "0",
//    "list": [
//      {
//        "actors": [
//          "[徐峥 袁泉 沈腾 吴云芳 陈奇 黄梅莹 欧丽娅 贾冰 郭京飞]"
//        ],
//        "areas": [
//          "[中国]"
//        ],
//        "directors": [
//          "[徐峥]"
//        ],
//        "discussion_hot": "789200",
//        "hot": "1.361e+06",
//        "id": "6399487713065566465",
//        "influence_hot": "789200",
//        "maoyan_id": "1250696",
//        "name": "囧妈",
//        "name_en": "Lost in Russia",
//        "poster": "https://p3-dy.bytecdn.cn/obj/compass/9ac412ae054b57f69c0967a8eb5e25c9",
//        "release_date": "2020-01-25",
//        "search_hot": "684900",
//        "tags": [
//          "[喜剧]"
//        ],
//        "topic_hot": "684900",
//        "type": "1"
//      }
//    ]
//  },
//  "extra": {
//    "description": "",
//    "error_code": "0",
//    "logid": "202008121419360101980821035705926A",
//    "now": "1597213176393",
//    "sub_description": "",
//    "sub_error_code": "0"
//  }
//}

// 失败
//{
//  "data": {
//    "description": "Parameter error",
//    "error_code": 2100005
//  },
//  "extra": {
//    "logid": "2020070614111601022506808001045D59",
//    "now": 1594015876138
//  }
//}

