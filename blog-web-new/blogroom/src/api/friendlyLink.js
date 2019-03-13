import request from '@/utils/request'

export function all() {
  return request({
    url: '/website/friendly/links/all',
    method: 'get'
  })
}

