import request from '@/utils/request'

export function allComments(id) {
  return request({
    url: '/article/comment/all/'+id,
    method: 'get'
  })
}
