import http from "./http";
//////////////////////////board start///////////////////
// board list
function bList(success, fail) {
  http.get(`/board`).then(success).catch(fail);
}

function bOrderList(success, fail) {
  http.get(`/board/order`).then(success).catch(fail);
}

// board write
function bWrite(board, success, fail) {
  http.post(`/board`, JSON.stringify(board)).then(success).catch(fail);
}

// board read
function bRead(bno, success, fail) {
  http.get(`/board/${bno}`).then(success).catch(fail);
}

// board modify
function bModify(board, success, fail) {
  http.put(`/board`, JSON.stringify(board)).then(success).catch(fail);
}

// board delete
function bDelete(bno, success, fail) {
  http.delete(`/board/${bno}`).then(success).catch(fail);
}
//////////////////////////board end///////////////////

//////////////////////////comment start///////////////////
// comment list
function cList(bno, success, fail) {
  http.get(`/comment/${bno}`).then(success).catch(fail);
}

// comment
function cRead(cno, success, fail) {
  http.get(`/comment/one/${cno}`).then(success).catch(fail);
}

// comment write
function cWrite(comment, success, fail) {
  http.post(`/comment`, JSON.stringify(comment)).then(success).catch(fail);
}

// comment modify
function cModify(comment, success, fail) {
  http.put(`/comment`, JSON.stringify(comment)).then(success).catch(fail);
}

// comment delete
function cDelete(cno, success, fail) {
  http.delete(`/comment/${cno}`).then(success).catch(fail);
}
//////////////////////////comment end///////////////////

//////////////////////////notice start///////////////////
// notice write
function nWrite(notice, success, fail) {
  http.post(`/notice`, JSON.stringify(notice)).then(success).catch(fail);
}

// notice delete
function nDelete(nno, success, fail) {
  http.delete(`/notice/${nno}`).then(success).catch(fail);
}

// notice modify
function nModify(notice, success, fail) {
  http.put(`/notice`, JSON.stringify(notice)).then(success).catch(fail);
}

// notice getNotices
function nList(success, fail) {
  http.get(`/notice`).then(success).catch(fail);
}

// notice getNotice4
function nList4(success, fail) {
  http.get(`/notice/n4`).then(success).catch(fail);
}

// notice getNotice
function nRead(nno, success, fail) {
  http.get(`/notice/${nno}`).then(success).catch(fail);
}
//////////////////////////notice end///////////////////

//////////////////////////qna start///////////////////

// qna write
function qWrite(qna, success, fail) {
  http.post(`/qna`, JSON.stringify(qna)).then(success).catch(fail);
}

// qna delete
function qDelete(qno, success, fail) {
  http.delete(`/qna/${qno}`).then(success).catch(fail);
}

// qna modifyUser
function qModifyUser(qna, success, fail) {
  http.put(`/qna`, JSON.stringify(qna)).then(success).catch(fail);
}

// qna getUserQna
function qgetUserQna(userid, success, fail) {
  http.get(`/qna/${userid}`).then(success).catch(fail);
}

// qna getUserNotCheck 
function qgetUserNotCheck(userid, success, fail) {
  http.get(`/qna/nchk/${userid}`).then(success).catch(fail);
}

// qna getUserAnswered
function qgetUserAnswered(userid, success, fail) {
  http.get(`/qna/all/${userid}`).then(success).catch(fail);
}

// qna getUserNotAnswer
function qgetUserNotAnswer(userid, success, fail) {
  http.get(`/qna/no/${userid}`).then(success).catch(fail);
}

// qna getManagerQna
function qgetManagerQna(success, fail) {
  http.get(`/qna`).then(success).catch(fail);
}

// qna getManagerNotAnswer
function qgetManagerNotAnswer(success, fail) {
  http.get(`/qna/mno`).then(success).catch(fail);
}

// qna getQna
function qRead(qno, success, fail) {
  http.get(`/qna/one/${qno}`).then(success).catch(fail);
}

// qna modifyManager 관리자가 답변하기
function qModifyManager(qna, success, fail) {
  http.put(`/qna/manager`, JSON.stringify(qna)).then(success).catch(fail);
}

//////////////////////////qna end///////////////////

export {
  bList,
  bOrderList,
  bWrite,
  bRead,
  bModify,
  bDelete,
  cList,
  cWrite,
  cRead,
  cModify,
  cDelete,
  nList,
  nWrite,
  nRead,
  nModify,
  nDelete,
  nList4,
  qDelete,
  qModifyManager,
  qModifyUser,
  qRead,
  qWrite,
  qgetManagerNotAnswer,
  qgetManagerQna,
  qgetUserAnswered,
  qgetUserNotAnswer,
  qgetUserNotCheck,
  qgetUserQna,
};
