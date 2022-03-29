var main = {
    init : function(){
    var _this = this;
    $('#btn-save').on('click', function() {
    _this.save();
    });
    },

save:function(){
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
    window.location.href = "/board/"+boardId;
    }).fail(function(error){
    alert(boardId);
    alert(JSON.stringify(error));
    }); //ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청을 한다
}

};
main.init();