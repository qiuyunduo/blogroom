import request from '@/utils/request'

export function uploadFile() {
  return request({
    url: '/article/thumb/save',
    method: 'post'
  })
}