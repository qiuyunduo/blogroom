import Cookies from 'js-cookie'

const tokenKey = 'User_Access_Token'
const userInfoKey = "Blogroom_User_Info"

export function isLogin() {
    let token = getToken()
    if(token === undefined || token === null || token === ''){
        return false
    }
    return true
}

export function getUserInfo() {
    let userInfo = Cookies.get(userInfoKey)
    if(userInfo !== undefined) {
        return JSON.parse(userInfo)
    } else {
        return {}
    }
}

export function setUserInfo(userInfo) {
    userInfo.addTime = transTIme(userInfo.addTime)
    userInfo.birthday = transBirthday(userInfo.birthday)
    return Cookies.set(userInfoKey,userInfo,{expires: 8})
}

export function removeUserInfo() {
    return Cookies.remove(userInfoKey)
}

export function getToken() {
    return Cookies.get(tokenKey)
}

export function refreshToken(refreshToken) {
    return Cookies.set(tokenKey,refreshToken)
}

export function setToken(token) {
    return Cookies.set(tokenKey,token,{expires: 8})
}

export function removeToken() {
    return Cookies.remove(tokenKey)
}

function transTIme(initTime) {
    if((initTime instanceof Array)){
        let date = initTime[0]+"-"+initTime[1]+"-"+initTime[2]
        let time = initTime[3]+":"+initTime[4]+":"+initTime[5]
        return date+' '+time
    } else {
        return initTime
    }
}

function transBirthday(timeStamp) {
    if((typeof(timeStamp)==="number")&&(timeStamp!==Infinity)&&!isNaN(timeStamp)){
        let time = new Date(timeStamp);
        let y = time.getFullYear();
        let m = time.getMonth()+1;
        m = m < 10 ? "0"+m : m
        let d = time.getDate();
        d = d < 10 ? "0"+d : d
        return y+'-'+m+'-'+d
    } else {
        return timeStamp
    }
}