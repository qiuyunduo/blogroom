import request from '@/utils/request'

export function query(data) {
  return request({
    url: '/user/user/query' ,
    method: 'get',
    params: data
  })
}

export function detailUser(userId) {
  return request({
    url: '/user/user/client/user/'+userId,
    method: 'get'
  })
}
