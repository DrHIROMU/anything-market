import { useEffect, useState } from "react";
import UploadFileService from "../../service/upload-file/upload-file.service";

function UploadFile(props: any){
    const [selectedFiles, setSelectedFiles] = useState([]);
    const [currentFile, setCurrentFile] = useState(undefined);
    const [progress, setProgress] = useState(0);
    const [message, setMessage] = useState("");
    const [fileInfos, setFileInfos] = useState([]);

    function selectFile(event:any){
        setSelectedFiles(event.target.files);
    }

    function upload(){
        let currentFile = selectedFiles[0];

        setProgress(0);
        setCurrentFile(currentFile);

        UploadFileService.upload(currentFile, (event: any) => {
          setProgress(Math.round(100 * event.loaded) / event.total);
        })
        .then((response) => {
          setMessage(response.data.message);
          return UploadFileService.getFiles();
        })
        .then((files)=>{
            setFileInfos(files.data);
        })
        .catch(()=>{
            setProgress(0);
            setMessage("Could not upload the file!");
            setCurrentFile(undefined);
        });

        setSelectedFiles([]);
    }

    useEffect(() => {
        UploadFileService.getFiles().then((response) =>{
            setFileInfos(response.data);
        })   
    });

    return (
      <div>
        {currentFile && (
          <div className="progress">
            <div
              className="progress-bar progress-bar-info progress-bar-striped"
              role="progressbar"
              aria-valuenow={progress}
              aria-valuemin="0"
              aria-valuemax="100"
              style={{ width: progress + "%" }}
            >
              {progress}%
            </div>
          </div>
        )}

        <label className="btn btn-default">
          <input type="file" onChange={this.selectFile} />
        </label>

        <button
          className="btn btn-success"
          disabled={!selectedFiles}
          onClick={this.upload}
        >
          Upload
        </button>

        <div className="alert alert-light" role="alert">
          {message}
        </div>

        <div className="card">
          <div className="card-header">List of Files</div>
          <ul className="list-group list-group-flush">
            {fileInfos &&
              fileInfos.map((file, index) => (
                <li className="list-group-item" key={index}>
                  <a href={file.url}>{file.name}</a>
                </li>
              ))}
          </ul>
        </div>
      </div>
    );
}

export default UploadFile;