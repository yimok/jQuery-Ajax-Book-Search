/**
 * Created by Yimo on 2016-11-24.
 */
function searchBook()
{

    //만약 enter key가 입력 되었을 경우 실행
    if(event.keyCode == 13)
    {
        //jsonp 방식으로 ajax를 이용해 호출
        $.ajax({
            url : "http://localhost:8080/last/list"   ,

            type : "GET",

            dataType : "jsonp" ,

            jsonp : "callback",

            data : {
                keyword : $("#keyword").val()

            },

            success : function(result) {

                $("tbody").empty();

                for (var i = 0; i < result.length; i++) {


                var tr = $("<tr></tr>");
                var img = $("<img>").attr("src", result[i].bimgurl);
                var imgtd = $("<td></td>").append(img);
                var titletd = $("<td></td>").append(result[i].btitle);
                var authortd = $("<td></td>").append(result[i].bauthor);
                var pricetd = $("<td></td>").append(result[i].bprice);
                /*
                <tr>
                    <td>
                        <input type = "button" valye ="삭제">
                    </td>
                </tr>
                 */

                var del = $("<input />").attr("type","button").attr("value","삭제").addClass("btn btn-danger");
                del.click(function(){


                    //AJAX로 실제 책을 DB에서 지워요!!
                    $.ajax

                    //화면에서 한줄을 지워요!
                    //this -> 현재 이벤트가 발생한 객체.
                    //$(this).remove();  // 이상태에서 지우면 삭제버튼만 제거됨
                    //this -> td -> tr 로 가서 지워야함
                    $(this).parent().parent().remove();
                });

                var deltd = $("<td></td>").append(del);

                tr.append(imgtd);
                tr.append(titletd);
                tr.append(authortd);
                tr.append(pricetd);
                tr.append(deltd);

                    $("tbody").append(tr);
                }
            },
            error : function(){

                alert("이상해요!!");

            }


        })

    }

}