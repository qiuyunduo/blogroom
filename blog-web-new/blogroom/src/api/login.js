import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/user/user/login',
    method: 'post',
    params: { 'name': data.userName, 'password': data.password }
  })
}

export function register(data) {
  return request({
    url: '/user/user/register',
    method: 'post',
    params: data
  })
}

export function logout(data) {
  return request({
    url: '/user/user/logout',
    method: 'post',
    params: data
  })
}