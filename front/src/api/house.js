import http from "./http";

function sidoList(success, fail) {
  http.get(`/house`).then(success).catch(fail);
}

function gugunList(sidoName, success, fail) {
  http.get(`/house/${sidoName}`).then(success).catch(fail);
}

function dongList({ sidoName, gugunName }, success, fail) {
  http.get(`/house/${sidoName}/${gugunName}`).then(success).catch(fail);
}

function sidoGugunCode({ sidoName, gugunName }, success, fail) {
  http.get(`/house/code/${sidoName}/${gugunName}`).then(success).catch(fail);
}

function houseList({ sidoName, gugunName, dongName }, success, fail) {
  http.get(`/house/${sidoName}/${gugunName}/${dongName}`).then(success).catch(fail);
}

function DealList(aptCode, success, fail) {
  http.get(`/house/detail/${aptCode}`).then(success).catch(fail);
}

function houseDetail(aptCode, success, fail) {
  http.get(`/house/detail/apt/${aptCode}`).then(success).catch(fail);
}

function interest(a, success, fail) {
  http.post(`/interest`, JSON.stringify( a )).then(success).catch(fail);
}
function uninterest(ino, success, fail) {
  http.delete(`/interest/${ino}`).then(success).catch(fail);
}
function interestList(userid, success, fail) {
  http.get(`/interest/${userid}`).then(success).catch(fail);
}
function count(aptcode, success, fail) {
  http.get(`/interest/cnt/${aptcode}`).then(success).catch(fail);
}
function getinterestId(aptcode, success, fail) {
  http.get(`/interest/find/${aptcode}`).then(success).catch(fail);
}

function getChild(sidogugun, success, fail) {
  http.get(`/frequent/child/${sidogugun}`).then(success).catch(fail);
}

function getOldman(sidogugun, success, fail) {
  http.get(`/frequent/oldman/${sidogugun}`).then(success).catch(fail);
}
function getBicycle(sidogugun, success, fail) {
  http.get(`/frequent/bicycle/${sidogugun}`).then(success).catch(fail);
}

export {
  sidoList,
  gugunList,
  dongList,
  houseList,
  DealList,
  houseDetail,
  interest,
  uninterest,
  interestList,
  count,
  getinterestId,
  sidoGugunCode,
  getChild,
  getOldman,
  getBicycle
};
