import Cookies from 'js-cookie'
import user from '../store/modules/user';

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
    return Cookies.set(userInfoKey,userInfo)
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
    return Cookies.set(tokenKey,token)
}

export function removeToken() {
    return Cookies.remove(tokenKey)
}