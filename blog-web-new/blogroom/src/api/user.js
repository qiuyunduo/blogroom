import request from '@/utils/request'

export function infoAll() {
  return request({
    url: '/user/info/all',
    method: 'get'
  })
}

export function login(data) {
  return request({
    url: '/user/login',
    method: 'post',
    params: { 'name': data.userName, 'password': data.password }
  })
}

export function register(data) {
  return request({
    url: '/user/register',
    method: 'post',
    params: data
  })
}
