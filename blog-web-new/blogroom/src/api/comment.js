import request from '@/utils/request'

export function createComment(data) {
  return request({
    url: '/article/comment/save',
    method: 'post',
    data
  })
}

export function allComments(id) {
  return request({
    url: '/article/comment/all/'+id,
    method: 'get'
  })
}
