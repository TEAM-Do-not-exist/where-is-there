import axios from "axios";
import router from "../../router";

const url = `${process.env.VUE_APP_SPRING_URL}/api/photo`;

const state = {
  photo: {},
  center: { lat: 33.450701, lng: 126.570667 }
};

const mutations = {
  getPhoto(state, pcode) {
    state.photo = {};
    state.center = { lat: 33.450701, lng: 126.570667 };
    axios.get(`${url}/selectOne/${pcode}`).then(r => {
      const { resvalue } = r.data;
      state.photo = resvalue;
      if (state.photo === null) {
        router.push("/error");
      }

      const kakaoUrl = "https://dapi.kakao.com/v2/local/search/keyword.json";
      const encoded = encodeURI(state.photo.pplace + state.photo.pname);
      axios
        .get(kakaoUrl + `?query=${encoded}`, {
          headers: {
            Authorization: `KakaoAK ${process.env.VUE_APP_KAKAO_API_KEY}`
          }
        })
        .then(r => {
          const { data } = r;
          const documents = data.documents;
          if (documents.length !== 0) {
            state.center.lng = documents[0].x;
            state.center.lat = documents[0].y;
          }
        });
    });
  }
};

const actions = {
  getPhotoAction(options, pcode) {
    options.commit("getPhoto", pcode);
  }
};

const getters = {
  photo(state) {
    return [state.photo, state.center];
  }
};

export default {
  state,
  mutations,
  actions,
  getters
};
