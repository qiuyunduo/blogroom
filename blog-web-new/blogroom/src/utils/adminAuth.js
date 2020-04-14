import Cookies from 'js-cookie'

const adminInfoKey = "Admin_Info"

export function isAdminLogin() {
    let adminInfo = getAdminInfo()
    if(adminInfo === undefined || adminInfo === null || adminInfo === ''){
        return false
    }
    return true
}
//因为是后台管理所以对管理员信息cookies不设置过期时间，既在关闭浏览器后默认清楚cookies
export function getAdminInfo() {
    let adminInfo = Cookies.get(adminInfoKey)
    if(adminInfo !== undefined) {
        return JSON.parse(adminInfo)
    } else {
        return null
    }
}

export function setAdminInfo(adminInfo) {
    adminInfo.addTime = transTIme(adminInfo.addTime)
    adminInfo.lastLoginTime = transTIme(adminInfo.lastLoginTime)
    return Cookies.set(adminInfoKey,adminInfo)
}

export function removeAdminInfo() {
    return Cookies.remove(adminInfoKey)
}
//export function registerModule

// export function getToken() {
//     return Cookies.get(tokenKey)
// }

// export function setToken(token) {
//     return Cookies.set(tokenKey,token)
// }
// export function removeToken() {
//     return Cookies.remove(tokenKey)
// }

function transTIme(initTime) {
    if((initTime instanceof Array)){
        let date = initTime[0]+"-"+initTime[1]+"-"+initTime[2]
        let time = initTime[3]+":"+initTime[4]+":"+initTime[5]
        return date+' '+time
    } else {
        return initTime
    }
}
