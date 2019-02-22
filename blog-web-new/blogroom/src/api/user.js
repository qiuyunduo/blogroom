import request from '@/utils/request'

export function infoAll() {
  return request({
    url: '/user/info/all',
    method: 'get'
  })
}
