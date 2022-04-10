var main = {
    init : function(){
    var _this = this;
//    $('#btn-save').on('click', function(){
//                    _this.save();
//    });
//회원가입
    $('#btn-member-join').on('click', function(){
                        _this.memberJoin();
    });
//댓글 작성
    $('#btn-comment-save').on('click', function() {
    _this.commentSave();
    });
//
//    $('#btn-childComment-save').on('click', function() {
//    _this.childCommentSave();
//    });

//댓글 삭제
    $('#btn-comment-delete').on('click', function() {
            _this.commentDelete();
     });
// 댓글 수정
    $('#btn-comment-update').on('click', function() {
        _this.commentUpdate();
    });
    $('#btn-like-up').on('click', function() {
            _this.LikeUp();
    });

    },
       save : function () {
                var data = {
                    title: $('#title').val(),
                    content:$('#content').val()
                };

                $.ajax({
                    type: 'POST',
                     url: "/board/write",
                    dataType: 'json',
                    contentType: 'application/json; charset=utf-8',
                    data: JSON.stringify(data)
                }).done(function(){//글 등록이 성공하면 메인페이지(/)로 이동

                    window.location.href = '/board';
                }).fail(function (error){
                    alert(JSON.stringify(error));
                });
            },

     memberJoin : function () {
            var data = {
                loginId: $('#loginId').val(),
                name:$('#name').val(),
                password:$('#password').val()
            };

            $.ajax({
                type: 'POST',
                 url: "/join",
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function(){
                alert('회원가입이 완료되었습니다');
                window.location.href = '/loginForm';
            }).fail(function (error){
                alert(JSON.stringify(error));
            });
        },
//부모댓글 저장
commentSave:function(){
    var data={
    content : $('#comment-content').val(),
    };
    var boardId = $('#boardId').val();


if(data['content'] === ""|| data['content'] ===" "){
            alert("댓글을 작성해주세요");
            return;
            }


   $.ajax({
    type: "POST",
    url: "/board/"+boardId+"/comment",
    dataType : 'json',
    contentType:'application/json; charset=utf-8',
    data: JSON.stringify(data)
    }).done(function(){

    location.href = "/board/"+boardId;
    }).fail(function(error){
    alert(JSON.stringify(error));
    }); //ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청을 한다
},
//자식댓글 저장
childCommentSave:function(parentId, boardId,content){
    var data={
    content : document.getElementById("child-content"+parentId).value
    };
    if(data['content'] === ""|| data['content'] ===" "){
                alert("댓글을 작성해주세요");
                return;
                }


    console.log(data['content']);
    console.log(parentId);
    console.log(boardId);
//if(data['content'] === ""|| data['content'] ===" "){
//            alert("댓글을 작성해주세요");
//            return;
//            }

   $.ajax({
    type: "POST",
    url: "/board/"+boardId+"/comment?parentId="+parentId,
    contentType:'application/json; charset=utf-8',
    data: JSON.stringify(data)
    }).done(function(){

    location.href = "/board/"+boardId;
    }).fail(function(error){
    alert(JSON.stringify(error));
    }); //ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청을 한다
},

commentUpdate:function(){
    var data={
    content : $('#comment-update-content').val(),
    };
    var boardId = $('#boardId').val();
    var commentId = $('#commentId').val();

    console.log(commentId);
    if(data['content'] === ""|| data['content'] ===" "){
            alert("내용이 없습니다");
            return;
   }


   $.ajax({
    type: "POST",
    url: "/board/comment/"+commentId+"/update",
    dataType : 'json',
    contentType:'application/json; charset=utf-8',
    data: JSON.stringify(data)
    }).done(function(){

    location.href = "/board/"+boardId;
    }).fail(function(error){
    alert(JSON.stringify(error));
    }); //ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청을 한다
},
   LikeUp : function (boardId,memberId) {
                var data = {
                 boardId,memberId
                };

                $.ajax({
                    type: 'POST',
                     url: "/board/"+boardId+"/like",
                    dataType: 'json',
                    contentType: 'application/json; charset=utf-8',
                    data: JSON.stringify(data)
                }).done(function(){
                    location.href = "/board/"+boardId;
                }).fail(function (error){
                    alert(JSON.stringify(error));
                });
            },

commentDelete:function(commentId,boardId){

//    var commentId = $('#commentId').val();
//    var boardId = $('#boardId').val();

console.log(commentId);

   $.ajax({
    type: "DELETE",
    url: "/board/comment/"+commentId,
    dataType : 'json',
    contentType:'application/json; charset=utf-8',
    }).done(function(){

    location.href = "/board/"+boardId;
    }).fail(function(error){

    alert(JSON.stringify(error));
    }); //ajax 통신을 이용해서 3개의 데이터를 json으로  변경하여 insert 요청을 한다
}

};
main.init();