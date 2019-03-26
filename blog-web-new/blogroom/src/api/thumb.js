import request from '@/utils/request'

export function addOne(data) {
  return request({
    url: '/article/thumb/save',
    method: 'post',
    data
  })
}

export function findThumb(articleId, userId) {
  return request({
    url: '/article/thumb/find',
    method: 'get',
    params: { 'articleId': articleId, 'userId': userId }
  })
}

export function delOne(id,articleId) {
  return request({
    url: '/article/thumb/remove',
    method: 'post',
    params: { 'id': id, 'articleId':articleId }
  })
}


export function countThumb(userId) {
  return request({
    url: '/article/thumb/count',
    method: 'get',
    params: { 'userId':userId }
  })
}
