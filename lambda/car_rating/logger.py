def log(level, fields):
  log_line = ""
  for key in fields:
    log_line = log_line + key + '="' + str(fields[key]) + '" '
  log_line = log_line.rstrip()
  print("level=" + level + " " + log_line)