import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/user/user/login',
    method: 'post',
    params: { 'account': data.account, 'password': data.password }
  })
}

export function register(data) {
  return request({
    url: '/user/user/register',
    method: 'post',
    params: data
  })
}

export function checkAccount(account) {
  return request({
    url: '/user/user/checkAccount',
    method: 'get',
    params: { 'account': account }
  })
}

export function checkEmail(email) {
  return request({
    url: '/user/user/checkEmail',
    method: 'get',
    params: { 'email': email }
  })
}

export function logout(userId) {
  return request({
    url: '/user/user/logout',
    method: 'post',
    params: { 'userId':userId }
  })
}

export function sendValidateCode(email,type) {
  return request({
    url: '/user/user/send/validateCode',
    method: 'post',
    params: { 'email': email, 'type': type}
  })
}

export function checkValidateCode(email,type,code) {
  return request({
    url: '/user/user/check/validateCode',
    method: 'post',
    params: { 'email': email, 'type': type, 'code': code}
  })
}

export function resetPwd(email,password) {
  return request({
    url: '/user/user/resetPassword',
    method: 'post',
    params: { 'email': email, 'password': password}
  })
}
