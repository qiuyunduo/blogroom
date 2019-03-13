import request from '@/utils/request'

export function getAllClass() {
  return request({
    url: '/article/class/all',
    method: 'get'
  })
}
