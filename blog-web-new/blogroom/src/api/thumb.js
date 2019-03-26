import request from '@/utils/request'

export function addOne() {
  return request({
    url: '/article/thumb/save',
    method: 'post'
  })
}

export function delOne() {
  return request({
    url: '/article/thumb/remove',
    method: 'post'
  })
}


export function countThumb(userId) {
  return request({
    url: '/article/thumb/count',
    method: 'get',
    params: { 'userId':userId }
  })
}
