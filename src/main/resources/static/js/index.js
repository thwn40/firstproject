var main = {
    init : function(){
    var _this = this;
//    $('#btn-save').on('click', function(){
//                    _this.save();
//    });
    $('#btn-member-join').on('click', function(){
                        _this.memberJoin();
    });


    $('#btn-comment-save').on('click', function() {
    _this.commentSave();
    });
    $('#btn-comment-delete').on('click', function() {
            _this.commentDelete();
     });

    $('#btn-comment-update').on('click', function() {
        _this.commentUpdate();
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
                    alert('글이 등록되었습니다.');
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
    alert("댓글이 등록되었습니다");
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
    alert("댓글이 수정되었습니다");
    location.href = "/board/"+boardId;
    }).fail(function(error){
    alert(JSON.stringify(error));
    }); //ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청을 한다
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
    alert("댓글이 삭제되었습니다");
    location.href = "/board/"+boardId;
    }).fail(function(error){

    alert(JSON.stringify(error));
    }); //ajax 통신을 이용해서 3개의 데이터를 json으로  변경하여 insert 요청을 한다
}

};
main.init();