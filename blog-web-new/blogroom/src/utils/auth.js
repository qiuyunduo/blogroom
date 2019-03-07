import Cookies from 'js-cookie'

const tokenKey = 'User_Access_Token'
const userInfoKey = "User_Info"

export function isLogin() {
    let token = getToken()
    if(token === undefined || token === null || token === ''){
        return false
    }
    return true
}

export function getUserInfo() {
    return Cookies.get(userInfoKey)
}

export function setUserInfo(userInfo) {
    return Cookies.set(userInfoKey,userInfo)
}

export function getToken() {
    return Cookies.get(tokenKey)
}

export function setToken(token) {
    return Cookies.set(tokenKey,token)
}

export function removeToken() {
    return Cookies.remove(tokenKey)
}