import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/website/admin/login',
    method: 'post',
    params: { 'name': data.username, 'password': data.password }
  })
}

export function loginOut(data) {
  return request({
    url: '/website/admin/loginOut',
    method: 'post',
    params: { 'id': data }
  })
}

export function findAllAdmin() {
  return request({
    url: '/website/admin/all',
    method: 'get'
  })
}

export function createAdmin(data) {
  return request({
    url: '/website/admin/create',
    method: 'post',
    data
  })
}

export function updateAdmin(data) {
  return request({
    url: '/website/admin/updateAdmin',
    method: 'post',
    data
  })
}

export function destoryAdmin(data) {
  return request({
    url: '/website/admin/delete',
    method: 'post',
    params: { 'id': data }
  })
}
