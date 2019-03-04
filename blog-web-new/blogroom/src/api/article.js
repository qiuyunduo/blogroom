import request from '@/utils/request'

export function allArticles(data) {
  return request({
    url: '/article/info/all',
    method: 'get',
    params: data
  })
}

export function readOne(id) {
  return request({
    url: '/article/info/'+id,
    method: 'get'
  })
}
