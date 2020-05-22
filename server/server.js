const express = require('express');
const bodyParser = require('body-parser');
const app = express();
//const sql = require('mssql');
const sql = require('mssql/msnodesqlv8');
var cors = require('cors');
app.use(cors());
 
// parse application/json
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended:false }));

var config = {
    driver: 'msnodesqlv8',
    server: 'DESKTOP-EC6LNC8\\SQLEXPRESS', 
    database: 'appdatabase' , 
    options: {
        trustedConnection: true
    }
};

//real code starts here 
app.get('/api/pelanggan', function (req, res) {
    sql.connect(config, function (err) {
        if (err) console.log(err);
        var request = new sql.Request();
        request.query('select * from pelanggan', function (err, recordset) {
            if (err) console.log(err)
            res.send(recordset);
        });
    });
});

app.get('/api/pelanggan/id', function (req, res) {
    sql.connect(config, function (err) {
        if (err) console.log(err);
        var request = new sql.Request();
        request.query('select * from mahasiswa where id='+req.body.id, function (err, recordset) {
            if (err) console.log(err)
            res.send(recordset);
        });
    });
});

app.post('/api/pelanggan', function (req, res) {
  sql.connect(config, function (err) {
      if (err) console.log(err);
      var request = new sql.Request();
      request.query("insert into pelanggan values ('"+req.body.nama+"','"+req.body.nomor+"','"+req.body.username+"','"+req.body.passwd+"')", function (err, recordset) {
          if (err) console.log(err);
          res.send(recordset);
        });
  });
});



app.put('/api/pelanggan', function (req, res) {
    sql.connect(config, function (err) {
        if (err) console.log(err);
        var request = new sql.Request();
        request.query("update pelanggan set nama='"+req.body.nama+"', nomor='"+req.body.nomor+"' where id="+req.body.id, function (err, recordset) {
            if (err) console.log(err);
            res.send(recordset);
        });
    });
  });

app.delete('/api/pelanggan', function (req, res) {
  
    sql.connect(config, function (err) {
        if (err) console.log(err);
        var request = new sql.Request();
        request.query("delete from pelanggan where id='"+req.body.id+"'", function (err,recordset) {
            if (err) console.log(err);
            res.send(recordset);
        });
       console.log('Deleted!')
    });
  });

//PAKET
 
app.get('/api/paket', function (req, res) {
    sql.connect(config, function (err) {
        if (err) console.log(err);
        var request = new sql.Request();
        request.query('select * from paket', function (err, recordset) {
            if (err) console.log(err)
            res.send(recordset);
        });
    });
});


app.get('/api/paketid', function (req, res) {
    sql.connect(config, function (err) {
        if (err) console.log(err);
        var request = new sql.Request();
        console.log(req.query.id);
        request.query("select * from paket where id='"+req.query.id+"'", function (err, recordset) {
            if (err) console.log(err);
            res.send(recordset.recordset[0]);
            console.log(recordset.recordset[0]);
        });
    });
});

app.post('/api/paket', function (req, res) {
  sql.connect(config, function (err) {
      if (err) console.log(err);
      var request = new sql.Request();
      request.query("insert into paket values ('"+req.body.namapaket+"','"+req.body.harga+"','"+req.body.deskripsi+"')", function (err, recordset) {
          if (err) console.log(err);
          res.send(recordset);
        });
  });
});


app.put('/api/paket', function (req, res) {
    sql.connect(config, function (err) {
        if (err) console.log(err);
        var request = new sql.Request();
        request.query("update paket set namapaket='"+req.body.namapaket+"', harga='"+req.body.harga+"', deskripsi='"+req.body.deskripsi+"' where id='"+req.body.id+"'", function (err, recordset) {
            if (err) console.log(err);
            res.send(recordset);
        });
    });
  });

app.delete('/api/paket', function (req, res) {
    sql.connect(config, function (err) {
        if (err) console.log(err);
        var request = new sql.Request();
        request.query("delete from paket where id='"+req.body.id+"'", function (err,recordset) {
            if (err) console.log(err);
            res.send(recordset);
        });
       console.log('Deleted!')
    });
  });


//Transaksi Admin
 
app.get('/api/transaksi', function (req, res) {
    sql.connect(config, function (err) {
        if (err) console.log(err);
        var request = new sql.Request();
        request.query('select * from transaksi order by tgl_masuk asc', function (err, recordset) {
            if (err) console.log(err)
            res.send(recordset);
        });
    });
});

app.get('/api/transaksiID', function (req, res) {
    sql.connect(config, function (err) {
        if (err) console.log(err);
        var request = new sql.Request();
        request.query('select * from transaksi where id_pelanggan='+req.query.id+'', function (err, recordset) {
            if (err) console.log(err)
            res.send(recordset);
        });
    });
});

app.post('/api/transaksi', function (req, res) {
  sql.connect(config, function (err) {
      console.log(req.body);
      if (err) console.log(err);
      var request = new sql.Request();
      request.query("insert into transaksi values ('"+req.body.id_pelanggan+"','"+req.body.id_paket+"','"+req.body.id_admin+"', CURRENT_TIMESTAMP,'"+req.body.berat+"', '0')", function (err, recordset) {
          if (err) console.log(err);
          res.send(recordset);
        });
  });
});

app.put('/api/transaksi', function (req, res) {
    sql.connect(config, function (err) {
        if (err) console.log(err);
        var request = new sql.Request();
        request.query("update transaksi set status_bayar='1' where id='"+req.body.id+"'", function (err, recordset) {
            if (err) console.log(err);
            res.send(recordset);
        });
    });
  });

app.delete('/api/transaksi', function (req, res) {
    sql.connect(config, function (err) {
        if (err) console.log(err);
        var request = new sql.Request();
        request.query("delete from transaksi where id='"+req.body.id+"'", function (err,recordset) {
            if (err) console.log(err);
            res.send(recordset);
        });
       console.log('Deleted!')
    });
  });

//LOGIN
app.post('/api/login', function (req, res) {
    sql.connect(config, function (err) {
        if (err) console.log(err);
        var request = new sql.Request();
        request.query("select * from pelanggan where username='"+req.body.username+"' AND passwd='"+req.body.passwd+"'", function (err, recordset) {
            if (err) console.log(err);
            res.send(recordset.recordset[0]);
        });
    });
  });

app.post('/api/loginadm', function (req, res) {
      sql.connect(config, function (err) {
          if (err) console.log(err);
          var request = new sql.Request();
          request.query("select * from pekerja where username='"+req.body.username+"' AND passwd='"+req.body.passwd+"'", function (err, recordset) {
              if (err) console.log(err);
              res.send(recordset.recordset[0]);
          });
      });
    });
/*
app.get('/', function (req, res) {
  res.send('Hello World');
});
*/
//lain2
//

app.get('/api/pendapatan', function (req, res) {
    sql.connect(config, function (err) {
        if (err) console.log(err);
        var request = new sql.Request();
        request.query('select sum(harga*berat) as stats from transaksi right join paket on transaksi.id_paket=paket.id', function (err, recordset) {
            if (err) console.log(err)
            res.send(recordset.recordset[0]);
        });
    });
});
app.get('/api/harian1', function (req, res) {
    sql.connect(config, function (err) {
        if (err) console.log(err);
        var request = new sql.Request();
        request.query('select count(id) as stats from transaksi where day(tgl_masuk)=day(CURRENT_TIMESTAMP)', function (err, recordset) {
            if (err) console.log(err)
            res.send(recordset.recordset[0]);
        });
    });
});
app.get('/api/harian2', function (req, res) {
    sql.connect(config, function (err) {
        if (err) console.log(err);
        var request = new sql.Request();
        request.query('select sum(harga*berat) as stats from transaksi right join paket on transaksi.id_paket=paket.id where day(tgl_masuk)=day(CURRENT_TIMESTAMP) and status_bayar=1', function (err, recordset) {
            if (err) console.log(err)
            res.send(recordset.recordset[0]);
        });
    });
});
//Pelanggan 
app.get('/api/belumdiambil1', function (req, res) {
    sql.connect(config, function (err) {
        if (err) console.log(err);
        var request = new sql.Request();
        request.query('select sum(harga*berat) as stats from transaksi right join paket on transaksi.id_paket=paket.id where id_pelanggan='+req.query.id+'  and status_bayar=0', function (err, recordset) {
            if (err) console.log(err)
            res.send(recordset.recordset[0]);
        });
    });
});

app.get('/api/total1', function (req, res) {
    sql.connect(config, function (err) {
        if (err) console.log(err);
        var request = new sql.Request();
        request.query('select sum(harga*berat) as stats from transaksi right join paket on transaksi.id_paket=paket.id where id_pelanggan=1 and status_bayar=1', function (err, recordset) {
            if (err) console.log(err)
            res.send(recordset.recordset[0]);
        });
    });
});

app.get('/api/belumdiambil', function (req, res) {
    sql.connect(config, function (err) {
        if (err) console.log(err);
        var request = new sql.Request();
        request.query('select count(id) as stats from transaksi where status_bayar=0 and id_pelanggan='+req.query.id+'', function (err, recordset) {
            if (err) console.log(err)
            res.send(recordset.recordset[0]);
        });
    });
});
app.get('/api/total', function (req, res) {
    sql.connect(config, function (err) {
        if (err) console.log(err);
        var request = new sql.Request();
        request.query('select count(id) as stats from transaksi where status_bayar=1 and id_pelanggan='+req.query.id+'', function (err, recordset) {
            if (err) console.log(err)
            res.send(recordset.recordset[0]);
        });
    });
});

var server = app.listen(8020, '192.168.100.12', function () {
    console.log('Server is running..');
});