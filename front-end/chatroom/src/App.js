import logo from "./logo.svg";
import "./App.css";
import {SockJS} from 'sockjs'
import {Stomp} from 'stomp'

function App() {
  return (
    <div className="App">
      <div class="row">
        <div class="col-md-6">
          <form class="form-inline" onSubmit={handleSubmit}>
            <div class="form-group">
              <label for="connect">WebSocket 連接:</label>
              <button id="connect" class="btn btn-default" type="submit" 
                disabled={connected} onClick={connect}>
                連接
              </button>
              <button
                id="disconnect"
                class="btn btn-default"
                type="submit"
                disabled={!connected}
                onClick={disconnect}
              >
                關閉連接
              </button>
            </div>
          </form>
        </div>
        <div class="col-md-6">
          <form class="form-inline">
            <div class="form-group">
              <label for="name">發送訊息</label>
              <input
                type="text"
                id="name"
                class="form-control"
                placeholder="請輸入訊息"
              />
            </div>
            <button id="send" class="btn btn-default" type="submit" onClick={sendName}>
              發送
            </button>
          </form>
        </div>
      </div>
      <div class="row">
        <div class="col-md-12">
          {
            connected?
            <table id="conversation" class="table table-striped">
              <thead>
                <tr>
                  <th>小海的聊天室</th>
                </tr>
              </thead>
              <tbody id="chatRoom">
                {chatDOMs}
              </tbody>
            </table>: ""                
          }          
        </div>
      </div>
    </div>
  );
}

let stompClient = null;
let connected = false;
let chatDOMs = [];

function handleSubmit(e){
  e.preventDefault();
}

function clearChat(){
  chatDOMs = [];
}

//連結WebSocket方法
function connect() {
  let socket = new SockJS("/endpointChatRoom"); //建立一個socket物件 名稱為:/endpointChatRoom
  stompClient = Stomp.over(socket);
  stompClient.connect({}, function (frame) {
    connected = true;
    clearChat();
    stompClient.subscribe("/topic/getResponse", function (response) {
      console.log(response);
      showConversation(JSON.parse(response.body).responseMessage); //
    });
  });
}

//關閉WebSocket方法
function disconnect() {
  if (stompClient !== null) {
    stompClient.disconnect();
  }
  connected = false;
  clearChat();
}

//傳送訊息方法
function sendName() {
  var name = $("#name").val();
  console.log(name);
  console.log(stompClient);
  stompClient.send("/messageControl", {}, JSON.stringify({ name: name }));
}
//顯示接收回來的訊息方法
function showConversation(responseMessage) {
  chatDOMs.push(`<tr><td>${responseMessage}</td></tr>`);
}

export default App;
