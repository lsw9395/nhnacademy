/**
 * 함수명이 대문자로 시작하면 관례적으로 생성자 함수 입니다.
 * @param {*} uiBtnPrevMonthId 이전 button id
 * @param {*} uiBtnNextMonthId  다음 button id
 * @param {*} uiBtnCurrentMonthId 오늘 button id
 * @returns
 */
function Navigator(uiBtnPrevMonthId, uiBtnNextMonthId, uiBtnCurrentMonthId){

    let year = null;
    let month = null;

    this.uiBtnPrevMonthId = uiBtnPrevMonthId;
    this.uiBtnNextMonthId = uiBtnNextMonthId;
    this.uiBtnCurrentMonthId = uiBtnCurrentMonthId;

    (function(){
        const searchParam = new URLSearchParams(document.location.search);
        year = searchParam.get("year");
        month = searchParam.get("month");
        const today = new Date();

    if(year == null){
        year = today.getFullYear();
    }

    if(month == null){
        month = today.getMonth() + 1;
        month = _convertToZeroMonthAndDay(month);
    }
    })();





    window.addEventListener("DOMContentLoaded",function(){

        let btnPrevMonth = document.getElementById(uiBtnPrevMonthId);
        let btnNextMonth = document.getElementById(uiBtnNextMonthId);
        let btnCurrentMonth = document.getElementById(uiBtnCurrentMonthId);

        https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Error
        if(btnPrevMonth == null){
            throw new Error("not find btnPrevMonth");
        }
        if(btnNextMonth == null){
            throw new Error("not find btnNextMonth");
        }
        if(btnCurrentMonth == null){
            throw new Error("not find btnCurrentMonth");
        }

        //버튼 이벤트 등록
        //이전
        btnPrevMonth.addEventListener("click",function(){
            if(month == 1){
                month = 12;
                year =  parseInt(year)-1;
            } else {
                month =parseInt(month)-1;
            }
            _navigate(year, month);
        });

        //다음
        btnNextMonth.addEventListener("click",function(){
            if(month == 12){
                month = 1;
                year = parseInt(year)+1;
            } else {
                month = parseInt(month)+1;
            }
            _navigate(year,month);
        });
        //오늘
        btnCurrentMonth.addEventListener("click",function(){
            const today = new Date();
            year = today.getFullYear();
            month = today.getMonth()+1;
            _navigate(year,month);
        });
    });


    function _navigate(targetYear,targetMonth){
        targetYear = _convertToZeroMonthAndDay(targetYear);
        targetMonth = _convertToZeroMonthAndDay(targetMonth);
        location.href = "./todo.html?year="+targetYear+"&month="+targetMonth;
    }
    function _convertToZeroMonthAndDay(d){
        d = parseInt(d);
        if(d<10){
            d = "0"+d;
        }
        return d;
    }

    return {
        getYear : function(){
            return year;
        },
        getMonth : function(){
            return month;
        },
        convertToZeroMonthAndDay : function(d){
            return _convertToZeroMonthAndDay(d);
        }
    }

}