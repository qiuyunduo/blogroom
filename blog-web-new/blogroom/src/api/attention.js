import request from '@/utils/request'

export function allFollowersOfUser(data) {
  return request({
    url: '/user/attention/attentions',
    method: 'get',
    params: data
  })
}

export function allFansOfUser(data) {
    return request({
      url: '/user/attention/attentions',
      method: 'get',
      params: data
    })
  }
