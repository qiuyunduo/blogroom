import request from '@/utils/request'

export function addIntegral(data) {
  return request({
    url: '/blog/integral/log/create',
    method: 'post',
    data
  })
}

export function query(data) {
  return request({
    url: '/blog/integral/log/query',
    method: 'get',
    params: data
  })
}