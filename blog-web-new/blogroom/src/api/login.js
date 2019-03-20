import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/user/user/login',
    method: 'post',
    params: { 'account': data.account, 'password': data.password }
  })
}

export function adminLogin(data) {
  return request({
    url: '/website/admin/login',
    method: 'post',
    params: { 'name': data.username, 'password': data.password }
  })
}

export function register(data) {
  return request({
    url: '/user/user/register',
    method: 'post',
    params: data
  })
}

export function logout(userId) {
  return request({
    url: '/user/user/logout',
    method: 'post',
    params: { 'userId':userId }
  })
}
