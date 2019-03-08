import request from '@/utils/request'

export function detailUser(userId) {
  return request({
    url: '/user/user/client/user/'+userId,
    method: 'get'
  })
}
