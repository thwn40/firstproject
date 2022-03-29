var main = {
    init : function(){
    var _this = this;
    $('#btn-save').on('click', function(){
                    _this.save();
    })
    $('#btn-comment-save').on('click', function() {
    _this.commentSave();
    })

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

commentSave:function(){
    var data={
    content : $('#comment-content').val(),
    };
    var boardId = $('#boardId').val();

//디폴트가 비동기 호출
console.log(data);

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
    alert(boardId);
    alert(JSON.stringify(error));
    }); //ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청을 한다
}

};
main.init();