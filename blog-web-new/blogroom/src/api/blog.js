import request from '@/utils/request'

export function blogDetail(userId) {
  return request({
    url: '/blog/blog/room/'+userId,
    method: 'get'
  })
}

export function addBlog(userId) {
  return request({
    url: '/blog/blog/create',
    method: 'post',
    params: userId
  })
}
