import request from '@/utils/request'

export function allArticles(data) {
  return request({
    url: '/article/article/all',
    method: 'get',
    params: data
  })
}

export function readOne(id) {
  return request({
    url: '/article/article/'+id,
    method: 'get'
  })
}

export function createOne(data) {
  return request({
    url: '/article/article/save',
    method: 'post',
    params: data
  })
}
