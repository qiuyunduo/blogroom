import request from '@/utils/request'

export function all() {
  return request({
    url: '/website/friendly/links/all',
    method: 'get'
  })
}

export function addLink(data) {
  return request({
    url: '/website/friendly/links/create',
    method: 'post'
  })
}

export function updateLink(data) {
  return request({
    url: '/website/friendly/links/update',
    method: 'post'
  })
}

export function delLink(linkId) {
  return request({
    url: '/website/friendly/links/remove/'+linkId,
    method: 'post',
  })
}
