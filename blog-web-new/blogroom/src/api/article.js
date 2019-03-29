import request from '@/utils/request'

export function allArticles(data) {
  return request({
    url: '/article/article/all',
    method: 'get',
    params: data
  })
}

export function fashionArticles() {
  return request({
    url: '/article/article/fashion',
    method: 'get'
  })
}

export function readOne(id) {
  return request({
    url: '/article/article/'+id,
    method: 'get'
  })
}

export function updateAuthor(author,userId) {
  return request({
    url: '/article/article/updateAuthor',
    method: 'post',
    params: { 'author': author, 'userId': userId }
  })
}

export function createOne(data) {
  return request({
    url: '/article/article/save',
    method: 'post',
    data
  })
}

export function updateOne(data) {
  return request({
    url: '/article/article/update',
    method: 'post',
    data
  })
}

export function deleteOne(id) {
  return request({
    url: '/article/article/delete/'+id,
    method: 'post'
  })
}

export function countArticles(userId) {
  return request({
    url: '/article/article/count',
    method: 'get',
    params: { 'userId':userId }
  })
}

export function countAllArticle() {
  return request({
    url: '/article/article/countAll',
    method: 'get'
  })
}

