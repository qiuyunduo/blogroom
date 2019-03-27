import request from '@/utils/request'

export function all() {
  return request({
    url: '/blog/integral/config/all',
    method: 'get'
  })
}

export function updateConfig(data) {
  return request({
    url: '/blog/integral/config/edit',
    method: 'post',
    data
  })
}