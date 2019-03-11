import request from '@/utils/request'

export function rankDetail(id) {
  return request({
    url: '/blog/rank/'+id,
    method: 'get'
  })
}
