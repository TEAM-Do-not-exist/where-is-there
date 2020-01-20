from django.urls import path
from . import views

urlpatterns = [
    path('', views.root, name='root'),
    path('insta/tour/', views.insta_tour, name='insta_tour'),
]
