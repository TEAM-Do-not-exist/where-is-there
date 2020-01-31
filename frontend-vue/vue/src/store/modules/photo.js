import axios from "axios";

const url = "http://127.0.0.1:8090/api/photo";

const state = {
  photo: {},
  center: { lat: 33.450701, lng: 126.570667 }
};

const mutations = {
  getPhoto(state, pcode) {
    axios
      .get(`${url}/selectOne/${pcode}`)
      .then(r => {
        const { resvalue } = r.data;
        state.photo = resvalue;

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
          })
          .catch();
      })
      .catch();
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
