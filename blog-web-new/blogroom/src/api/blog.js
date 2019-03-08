import request from '@/utils/request'

export function blogDetail(userId) {
  return request({
    url: '/blog/blog/room/'+userId,
    method: 'get'
  })
}
