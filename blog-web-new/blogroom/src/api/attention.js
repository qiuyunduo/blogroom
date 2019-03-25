import request from '@/utils/request'

export function findOne(data) {
  return request({
    url: '/user/attention/find',
    method: 'get',
    params: data
  })
}

export function attention(data) {
  return request({
    url: '/user/attention/save',
    method: 'post',
    params: data
  })
}

export function removeAttention(data) {
  return request({
    url: '/user/attention/remove',
    method: 'post',
    params: data
  })
}

export function allFollowersOfUser(data) {
  return request({
    url: '/user/attention/attentions',
    method: 'get',
    params: data
  })
}

export function allFansOfUser(data) {
    return request({
      url: '/user/attention/fans',
      method: 'get',
      params: data
    })
}


export function allFollowers(user1Id) {
  return request({
    url: '/user/attention/attentions/all',
    method: 'get',
    params: { 'user1Id' : user1Id }
  })
}
