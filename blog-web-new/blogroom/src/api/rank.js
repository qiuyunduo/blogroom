import request from '@/utils/request'

export function allRanks() {
  return request({
    url: '/blog/rank/all',
    method: 'get'
  })
}

export function rankDetail(id) {
  return request({
    url: '/blog/rank/'+id,
    method: 'get'
  })
}

export function updateRank(data) {
  return request({
    url: '/blog/rank/update',
    method: 'post',
    data
  })
}
