$(document).ready(function() {
    /*
        *  参数详解:
        *	upTime			上移的时间
        *	downTime		下落的时间
        *	beatHeight		上移高度
        *	isAuth			是否自动
        *	isRotate		是否旋转
    */
    $('p#beatText').beatText({isAuth:false,isRotate:false});
    $('p#rotateText').beatText({isAuth:false,isRotate:true});
    $('p#autoText').beatText({isAuth:true,beatHeight:"3em",isRotate:false});
    $('p#roloadText').beatText({isAuth:true,beatHeight:"1em",isRotate:false,upTime:100,downTime:100});
    $('p#autoRotateText').beatText({isAuth:true,upTime:700,downTime:700,beatHeight:"3em",isRotate:true});

});