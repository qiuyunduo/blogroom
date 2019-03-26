import request from '@/utils/request'

export function visit(userId) {
  return request({
    url: '/blog/blog/room/visit',
    method: 'get',
    params: { 'userId':userId }
  })
}

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
    params: { 'userId':userId }
  })
}

export function updateBlog(data) {
  return request({
    url: '/blog/blog/update',
    method: 'post',
    data
  })
}
