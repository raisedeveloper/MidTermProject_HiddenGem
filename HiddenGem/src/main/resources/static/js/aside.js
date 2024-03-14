/* 
 * Aside menu Control
 */

$(document).ready(function () { // event 등록
  $('#stateMsgBtn').click(function (e) {
    $('#stateMsgInput').attr({ 'class': 'mt-2' }); // 입력창(stateMsgInput) class의 d-none 을 없애줌. -> 보이게 해줌.
    $('#stateInput').val($('#stateMsg').text()); // 입력창에 stateMsg 내용이 보이게
  });
  $('#stateMsgSubmit').click(changeStateMsg); // 이벤트 등록 - 오늘의 한줄 변경

  $('#getWeatherButton').click(getWeather); // 날씨
});


function changeStateMsg() {
  let stateInputVal = $('#stateInput').val(); // 사용자가 수정한 글 읽기
  $('#stateMsgInput').attr({ 'class': 'mt-2 d-none' }); // 입력창(stateMsgInput) 다시 안보이게
  $.ajax({  // Asychronous Javascript And XML - 화면에 일부분만 변경할 때 주로 사용
    type: 'GET', // 서버로 전송
    url: '/abbs/aside/stateMsg',
    data: { stateMsg: stateInputVal },
    success: function (result) {
      console.log('state message: ', stateInputVal, result); // 잘 갔는지 확인
      $('#stateMsg').html(stateInputVal); // 변경한 값을 표시
    }
  });
}

function getWeather() {
  $.ajax({
    type: 'GET',
    url: '/abbs/aside/weather',
    success: function (result) {
      $('#weather').html(result);
    }
  });
}