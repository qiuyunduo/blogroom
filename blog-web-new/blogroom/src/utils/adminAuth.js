import Cookies from 'js-cookie'

const adminInfoKey = "Admin_Info"

export function isLogin() {
    let adminInfo = getAdminInfo()
    if(adminInfo === undefined || adminInfo === null || adminInfo === ''){
        return false
    }
    return true
}

export function getAdminInfo() {
    return Cookies.get(adminInfoKey)
}

export function setAdminInfo(adminInfo) {
    return Cookies.set(adminInfoKey,adminInfoKey)
}

export function removeAdminInfo() {
    return Cookies.remove(adminInfoKey)
}

// export function getToken() {
//     return Cookies.get(tokenKey)
// }

// export function setToken(token) {
//     return Cookies.set(tokenKey,token)
// }

// export function removeToken() {
//     return Cookies.remove(tokenKey)
// }