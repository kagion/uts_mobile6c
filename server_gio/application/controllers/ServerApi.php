<?php
defined('BASEPATH') OR exit('No direct script access allowed');
class ServerApi extends CI_Controller {

    // fungsi untuk CREATE
    public function addToko()
    {
          // deklarasi variable
          $name = $this->input->post('name');
          $hp = $this->input->post('hp');
        $alamat = $this->input->post('alamat');
          // isikan variabel dengan nama file
          $data['toko_name'] = $name;
        $data['toko_hp'] = $hp;
          $data['toko_alamat'] = $alamat;
          $q = $this->db->insert('tb_toko', $data);
          // check insert berhasil apa nggak
          if ($q) {
            $response['pesan'] = 'insert berhasil';
            $response['status'] = 200;
          } else {
            $response['pesan'] = 'insert error';
            $response['status'] = 404;
          }
          echo json_encode($response);
    }
      // fungsi untuk READ
    public function getDataToko()
    {
          $q = $this->db->get('tb_toko');
          if ($q -> num_rows() > 0) {
            $response['pesan'] = 'data ada';
            $response['status'] = 200;
            // 1 row
            $response['toko'] = $q->row();
            $response['toko'] = $q->result();
          } else {
            $response['pesan'] = 'data tidak ada';
            $response['status'] = 404;
          }
          echo json_encode($response);
    }
      // fungsi untuk DELETE
    public function deleteToko()
    {
          $id = $this->input->post('id');
          $this->db->where('toko_id', $id);
          $status = $this->db->delete('tb_toko');
          if ($status == true) {
            $response['pesan'] = 'hapus berhasil';
            $response['status'] = 200;
          } else {
            $response['pesan'] = 'hapus error';
            $response['status'] = 404;
          }
          echo json_encode($response);
    }
      // fungsi untuk UPDATE
    public function updateToko()
    {
          // deklarasi variable
          $id = $this->input->post('id');
          $name = $this->input->post('name');
          $hp = $this->input->post('hp');
          $alamat = $this->input->post('alamat');
          $this->db->where('toko_id', $id);
          // isikan variabel dengan nama file
          $data['toko_name'] = $name;
          $data['toko_hp'] = $hp;
          $data['toko_alamat'] = $alamat;
          $q = $this->db->update('tb_toko', $data);
          // check insert berhasil apa nggak
          if ($q) {
            $response['pesan'] = 'update berhasil';
            $response['status'] = 200;
          } else {
            $response['pesan'] = 'update error';
            $response['status'] = 404;
          }
          echo json_encode($response);
    }
}